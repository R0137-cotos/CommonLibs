package jp.co.ricoh.cotos.commonlib.logic.barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Component;

@Component
public class BarcodeGenerator {

	/**
	 * バーコードを生成する
	 *
	 * @param barcodeBean 種類別バーコードBeanインスタンス
	 * @param resolution 解像度
	 * @param code バーコード下対象文字列
	 * @return バーコード画像バイト配列
	 * @throws IOException
	 */
	public byte[] generate(AbstractBarcodeBean barcodeBean, int resolution, String code) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(outputStream, "image/x-png", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		barcodeBean.generateBarcode(canvas, code);
		canvas.finish();

		return outputStream.toByteArray();
	}
}
