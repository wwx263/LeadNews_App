package com.heima.admin.controller.v1;

import com.heima.admin.service.AdChannelService;
import com.heima.apis.admin.AdChannelControllerApi;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class AdChannelController implements AdChannelControllerApi {

    /*频道服务 在admin模块下,调用自己包下服务使用autowired 远程则用 reference*/
    @Autowired
    private AdChannelService adChannelService;

    /**
     * 根据名称模糊查询对应结果
     */
    @PostMapping("/list")
    @Override
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto) {
        if (null == dto) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return adChannelService.findByNameAndPage(dto);
    }

    /**
     * 保存频道
     * @param channel
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody AdChannel channel) {
        if (null == channel) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        return adChannelService.insert(channel);
    }

    /**
     * 更新频道
     * @param channel
     * @return
     */
    @PostMapping("/update")
    @Override
    public ResponseResult update(@RequestBody AdChannel channel) {
        if (null == channel) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        return adChannelService.updateChannel(channel);
    }

    /**
     * 用id删除频道
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    @Override
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        if (id==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return adChannelService.deleteChannelById(id);
    }


    //-------------------------------------------------------------------------------------------------------------------
    //    public static ExecutorService threadPool = Executors.newFixedThreadPool(2);
//异步编排CompletableFuture.supplyAsync()
//    public static void main(String[] args) throws ExecutionException, InterruptedException {

              /*  System.out.println("main ==>start ");
                CompletableFuture future02 = CompletableFuture.supplyAsync(() -> {
                    System.out.println("当前线程=> " + Thread.currentThread().getName());
                    int i = 10 / 2;
                    System.out.println("运行结果=> " + i);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;

                }, threadPool).thenAcceptAsync(res->{
                    System.out.println("线程B 去启动，并且获得了上一个的线程返回值"+ res);
                },threadPool);*/



       /* CompletableFuture future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程=> " + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("运行结果=> " + i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }, threadPool).thenRunAsync(()->{
            System.out.println("线程B 去启动，并且没法接受线程1的返回值");
        },threadPool);*/



//
//        CompletableFuture future03 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程=> " + Thread.currentThread().getName());
//            int i = 10 / 2;
//            System.out.println("运行结果=> " + i);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return i;
//            //  R apply(T t);
//        }, threadPool).thenApplyAsync(res -> {
//            System.out.println("线程B 去启动，并且接受线程1的返回值" + res);
//            return "线程B 也返回了结果";
//        }, threadPool);
//
//        System.out.println("main ==>end ");
//        System.out.println(future03.get());
//    }
}
